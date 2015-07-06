package com.ibm.gbs.tramitator.jsf.mbean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.apache.log4j.Logger;
import org.richfaces.component.SortOrder;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import com.ibm.gbs.tramitator.jpa.util.Criterio;
import com.ibm.gbs.tramitator.util.UtilReflection;

public abstract class PaginacionDataModel<T> extends ExtendedDataModel<T>
		implements Arrangeable {

	private Logger log = Logger.getLogger(PaginacionDataModel.class);

	protected Class<T> typeParameterClass;

	public PaginacionDataModel(Class<T> typeParameterClass) {
		super();
		this.typeParameterClass = typeParameterClass;
	}

	private Object rowKey;

	private int ultimaFaseArrange = 0;
	private boolean sortFieldFromDB = true;
	private Map<Object,Integer> rowIndex;
	
//	private CountBean countBean;
//
//	public CountBean getCountBean() {
//		return countBean;
//	}
//
//	public void setCountBean(CountBean countBean) {
//		this.countBean = countBean;
//	}

	private ArrangeableState arrangeableState;

	public void arrange(FacesContext context, ArrangeableState state) {
		int fase = context.getCurrentPhaseId().getOrdinal();
		if (ultimaFaseArrange <= fase) {
			updateModel(data);
			ultimaFaseArrange = fase;

			if (mustCheckReLoad()) {
				checkReLoadData(state);
			} else {
				data = null;
				rowCount = null;
			}
			arrangeableState = state;
			// sortFieldFromDB = sortFieldFromDB(state);
			// if (sortFieldFromDB) {
			// data = null;
			// rowCount = null;
			// }
			// arrangeImpl(context, state);
		}
		ultimaFaseArrange = fase;
	}

	protected boolean mustCheckReLoad() {
		return false;
	}

	private void checkReLoadData(ArrangeableState state) {
		if (arrangeableState != null && state != null) {
			log.debug("PaginacionDataModel.arrange comprobando si hay que actualizar de BBDD Inicio");
			boolean equal = true;

			if (arrangeableState.getFilterFields() != null
					&& state.getFilterFields() != null) {
				if (arrangeableState.getFilterFields().size() == state
						.getFilterFields().size()) {
					for (int i = 0; i < arrangeableState.getFilterFields()
							.size() && equal; i++) {
						equal = filterEqual(equal, arrangeableState
								.getFilterFields().get(i), state
								.getFilterFields().get(i));
					}
				} else {
					equal = false;
				}
			} else if (arrangeableState.getFilterFields() != null
					|| state.getFilterFields() != null) {
				equal = false;
			}

			if (!equal) {
				data = null;
			} else {
				if (arrangeableState.getSortFields() != null
						&& state.getSortFields() != null) {
					if (arrangeableState.getSortFields().size() == state
							.getSortFields().size()) {
						for (int i = 0; i < arrangeableState
								.getSortFields().size() && equal; i++) {
							equal = sortEqual(equal, arrangeableState
									.getSortFields().get(i), state
									.getSortFields().get(i));
						}
					} else {
						equal = false;
					}
				} else if (arrangeableState.getFilterFields() != null
						|| state.getFilterFields() != null) {
					equal = false;
				}
				if (!equal) {
					data = null;
				}
			}
			// if (state.getFilterFields() != null) {
			// if (!state.getFilterFields().equals(
			// arrangeableState.getFilterFields())) {
			// data = null;
			// }
			// }
			// if (state.getSortFields() != null) {
			// if (!state.getSortFields().equals(
			// arrangeableState.getSortFields())) {
			// data = null;
			// }
			// }
			log.debug("PaginacionDataModel.arrange comprobando si hay que actualizar de BBDD Resultado: -"
					+ equal + "-");
			log.debug("PaginacionDataModel.arrange comprobando si hay que actualizar de BBDD Fin");
		}
	}

	private boolean sortEqual(boolean equal, SortField filter1,
			SortField filter2) {
		if (!
		// 1ª Comprobación
		(((filter1.getSortBy() != null && filter1.getSortBy().equals(
				filter2.getSortBy())) || (filter1.getSortBy() == null && filter2
				.getSortBy() == null))
		// 2ª Comprobación
		&& ((filter1.getSortOrder() != null && filter1.getSortOrder().equals(
				filter2.getSortOrder())) || (filter1.getSortOrder() == null && filter2
				.getSortOrder() == null)))) {
			equal = false;
		}
		return equal;
	}

	private boolean filterEqual(boolean equal, FilterField filter1,
			FilterField filter2) {
		if (!(
		// 1º Comprobación
		((filter1.getFilterExpression() != null && filter1
				.getFilterExpression().equals(filter2.getFilterExpression())) || (filter1
				.getFilterExpression() == null && filter2.getFilterExpression() == null))
				// 2º Comprobación
				&& ((filter1.getFilter() != null && filter1.getFilter().equals(
						filter2.getFilter())) || (filter1.getFilter() == null && filter2
						.getFilter() == null))
		// 3ª Comprobación
		&& ((filter1.getFilterValue() != null && filter1.getFilterValue()
				.equals(filter2.getFilterValue())) || (filter1.getFilterValue() == null && filter2
				.getFilterValue() == null)))) {
			equal = false;
		}
		return equal;
	}

	/**
	 * Comprueba que el campo por el que se ordena está en BBDD
	 * 
	 * @param arrangeableState
	 * @return
	 */
	protected boolean sortFieldFromDB(ArrangeableState arrangeableState) {
		return true;
	}

	@Override
	public void setRowKey(Object key) {
		rowKey = key;
	}

	@Override
	public Object getRowKey() {
		return rowKey;
	}

	private List<T> data = null;

	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) {
		if (data == null || data.size() == 0) {
			arrangeImpl(context, arrangeableState);
			SequenceRange sr = (SequenceRange) range;
			criterio.setPrimero(sr.getFirstRow());
			criterio.setNum(sr.getRows());
//			countBean = new CountBean();
			data = walk(criterio);
		} else if (!sortFieldFromDB) {
			SequenceRange sr = (SequenceRange) range;
			criterio.setPrimero(sr.getFirstRow());
			criterio.setNum(sr.getRows());
//			countBean = new CountBean();
			data = walk(criterio, data);
		}
		int i = 0;
		rowIndex = new HashMap<Object, Integer>();
		for (T t : data) {
			rowIndex.put(getId(t), i);
			i++;
		}
		for (T t : data) {
			visitor.process(context, getId(t), argument);
		}
	}

	/**
	 * Ordenación campos que no están en BBDD
	 * 
	 * @param criterio2
	 * @param data2
	 */
	protected List<T> walk(Criterio criterio, List<T> data) {
		return data;
	}

	@Override
	public boolean isRowAvailable() {
		return rowKey != null;
	}

	private Integer rowCount;

	@Override
	public int getRowCount() {
		criterio.clear();
		initCriterio(criterio);
			rowCount = getRowCountImpl(criterio);

		return rowCount;
	}

	@Override
	public T getRowData() {
		return find(rowKey);
	}

	@Override
	public int getRowIndex() {
		if (rowIndex == null || rowIndex.size() == 0) {
			return -1;
		}
		return rowIndex.get(rowKey);
	}
	
	@Override
	public void setRowIndex(int rowIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWrappedData(Object data) {
		throw new UnsupportedOperationException();
	}

	protected abstract List<T> walk(Criterio criterio);

	protected void updateModel(List<T> model) {
	}

	protected abstract int getRowCountImpl(Criterio criterio);

	private Criterio criterio = new Criterio();

	protected Criterio criteriosIniciales = null;

	private void arrangeImpl(FacesContext context,
			ArrangeableState arrangeableState) {
		if (arrangeableState != null)
			for (FilterField filtro : arrangeableState.getFilterFields()) {
				convertirFiltro2Criterio(filtro);
			}

		if (arrangeableState != null)
			for (SortField sort : arrangeableState.getSortFields()) {
				String fs = sort.getSortBy().getExpressionString();
				Object fv = sort.getSortOrder();
				if (fv != null && !"".equals(fv) && fs.length() > 5) {
					String criterioStr = fs.substring(3, fs.length() - 2);
					boolean isEmbedded = false;
					try {
						isEmbedded = UtilReflection.isEmbeddedId(criterioStr,
								typeParameterClass);
					} catch (Exception e) {
						log.error("PaginacionDataModel.arrangeImpl", e);
					}
					if (isEmbedded) {
						ordenarEmbeddedId(criterioStr, typeParameterClass,
								fv.equals(SortOrder.ascending) ? "ASC" : "DESC");
					} else {
						criterio.addOrder(criterioStr,
								fv.equals(SortOrder.ascending) ? "ASC" : "DESC");
					}
				}
			}
	}

	@SuppressWarnings("rawtypes")
	protected void ordenarEmbeddedId(String campo, Class clazz, String order) {
		try {
			Class idClazz = UtilReflection.obtenerClassType(campo,
					typeParameterClass);
			for (Field field : idClazz.getDeclaredFields()) {
				Annotation annotation = field
						.getAnnotation(javax.persistence.Transient.class);
				if (annotation != null) {
					continue;
				} else if (Modifier.isStatic(field.getModifiers())){
					continue;
				}
				criterio.addOrder(field.getName(), order);
			}
		} catch (SecurityException e) {
			log.error("PaginacionDataModel.ordenarEmbeddedId", e);
		} catch (NoSuchFieldException e) {
			log.error("PaginacionDataModel.ordenarEmbeddedId", e);
		}
	}

	protected void convertirFiltro2Criterio(FilterField filtro) {
		String fs = filtro.getFilterExpression().getExpressionString();
		Object fv = filtro.getFilterValue();
		log.debug("PaginacionDataModel.convertirFiltro2Criterio fs="+fs+" fv="+fv+"-");
		if (fv != null && !"".equals(fv) && fs.length() > 5) {
			boolean filtroLike = false;
			boolean filtroBetween = false;
			String criterioStr = fs.substring(3, fs.length() - 2);
			if (criterioStr.startsWith("LIKE_")) {
				criterioStr = criterioStr.substring(5);
				filtroLike = true;
			} else if (criterioStr.startsWith("BETWEEN_")) {
				criterioStr = criterioStr.substring(8);
				filtroBetween = true;
			}

			if (!checkCriterioValido(criterioStr))
				return;

			if (filtroBetween) {
				String[] valores = fv.toString().split(",");
				Object valorDesde = null;
				Object valorHasta = null;
				if (valores.length >= 1) {
					valorDesde = valores[0];
					// Si falla la conversión no se toma en cuenta el
					// filtro
					valorDesde = obtenerFiltro(valorDesde, criterioStr);
					if (valorDesde == null) {
						return;
					}

					if (valores.length == 2 && valores[1].trim().length() > 0) {
						valorHasta = valores[1];
						valorHasta = obtenerFiltro(valorHasta, criterioStr);
						if (valorHasta != null) {
							Calendar cal = Calendar.getInstance();
							cal.setTime((Date) valorHasta);
							cal.add(Calendar.MILLISECOND, 999);
							cal.add(Calendar.SECOND, 59);
							cal.add(Calendar.MINUTE, 59);
							cal.add(Calendar.HOUR, 23);
							valorHasta = cal.getTime();
						}
					}
				}
				criterio.addFiltroGe(criterioStr, valorDesde);
				criterio.addFiltroLe(criterioStr, valorHasta);
			} else {
				// Si falla la conversión no se toma en cuenta el filtro
				fv = obtenerFiltro(fv, criterioStr);
				if (fv == null) {
					return;
				}

				if (filtroLike)
					criterio.addFiltroLike(criterioStr, fv);
				else
					criterio.addFiltro(criterioStr, fv);
			}
		}
	}

	/**
	 * Si el campo por el que se va a filtrar no existe no se continua con el
	 * procesamiento
	 * 
	 * @param criterioStr
	 * @return
	 */
	protected boolean checkCriterioValido(String criterioStr) {
		try {
			UtilReflection.obtenerClassType(criterioStr, typeParameterClass);
			// String[] split = criterioStr.split("\\.");
			//
			// Class<T> parameterClass = this.typeParameterClass;
			// for (int i = 0; i < split.length ; i++){
			// Field field = parameterClass.getDeclaredField(split[i]);
			// System.out.println(field.getType());
			// parameterClass = (Class<T>) field.getType();
			// }
		} catch (Exception e) {
			log.error("PaginacionDataModel.checkCriterioValido", e);
			return false;
		}
		return true;
	}

	private Object obtenerFiltro(Object fv, String criterioStr) {
		Object value = null;

		@SuppressWarnings("rawtypes")
		Class claseTipo = null;
		try {
			claseTipo = UtilReflection.obtenerClassType(criterioStr,
					typeParameterClass);
		} catch (Exception e) {
			log.error("PaginacionDataModel.obtenerFiltro", e);
			return null;
		}
		if (claseTipo.isPrimitive()) {
			value = UtilReflection.paresearPrimitivas(fv, claseTipo);
			return value;
		}
		value = UtilReflection.paresearValoresNumericos(fv, claseTipo);
		
		if(value == null)
		{
			value = UtilReflection.paresearValoresBoolean(fv, claseTipo);
		}
		
		if (value == null) {
			try {
				value = UtilReflection.paresearValoresTemporales(fv, claseTipo);
			} catch (ParseException e1) {
				log.error("PaginacionDataModel.obtenerFiltro", e1);
			}
			if (value == null)
				try {
					if (claseTipo.equals(String.class)) {
						value = fv.toString();
					}
				} catch (Exception e) {
					log.error("PaginacionDataModel.obtenerFiltro", e);
				}
		}
		return value;

	}

	// @SuppressWarnings("unchecked")
	// private Class<T> obtenerClassType(String criterioStr)
	// throws NoSuchFieldException {
	// String[] split = criterioStr.split("\\.");
	//
	// Class<T> parameterClass = this.typeParameterClass;
	// for (int i = 0; i < split.length; i++) {
	// Field field = parameterClass.getDeclaredField(split[i]);
	// System.out.println(field.getType());
	// parameterClass = (Class<T>) field.getType();
	// }
	// return parameterClass;
	// }
	//
	// @SuppressWarnings("rawtypes")
	// private Object paresearPrimitivas(Object fv, Class tipo) {
	// log.debug("PaginacionDataModel.paresearPrimitivas Inicio");
	// Object value = null;
	// try {
	// if (Byte.TYPE.equals(tipo)) {
	// byte valor = Byte.parseByte((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas byte");
	// } else if (Short.TYPE.equals(tipo)) {
	// short valor = Short.parseShort((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas short");
	// } else if (Integer.TYPE.equals(tipo)) {
	// int valor = Integer.parseInt((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas int");
	// } else if (Long.TYPE.equals(tipo)) {
	// long valor = Long.parseLong((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas long");
	// } else if (Float.TYPE.equals(tipo)) {
	// float valor = Float.parseFloat((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas float");
	// } else if (Double.TYPE.equals(tipo)) {
	// double valor = Double.parseDouble((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas double");
	// } else if (Boolean.TYPE.equals(tipo)) {
	// boolean valor = Boolean.parseBoolean((String) fv);
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas boolean");
	// } else if (Character.TYPE.equals(tipo)) {
	// char valor = (Character) fv;
	// value = valor;
	// log.debug("PaginacionDataModel.paresearPrimitivas char");
	// }
	// log.debug("PaginacionDataModel.paresearPrimitivas -" + value + "-");
	// } catch (Exception e) {
	// log.error("PaginacionDataModel.paresearPrimitivas");
	// }
	//
	// log.debug("PaginacionDataModel.paresearPrimitivas Fin");
	// return value;
	// }
	//
	// @SuppressWarnings("rawtypes")
	// private Object paresearValoresNumericos(Object fv, Class tipo) {
	// log.debug("PaginacionDataModel.paresearValoresNumericos inicio");
	// Object value = null;
	// try {
	// if (tipo.equals(Long.class)) {
	// value = new Long(fv.toString());
	// } else if (tipo.equals(Integer.class)) {
	// value = new Integer(fv.toString());
	// } else if (tipo.equals(BigDecimal.class)) {
	// value = new BigDecimal(fv.toString());
	// }
	// } catch (Exception e) {
	// log.error("PaginacionDataModel.paresearValoresNumericos");
	// }
	// log.debug("PaginacionDataModel.paresearValoresNumericos Fin -" + value
	// + "-");
	// return value;
	// }
	//
	// @SuppressWarnings("rawtypes")
	// private Object paresearValoresTemporales(Object fv, Class tipo) {
	// log.debug("PaginacionDataModel.paresearValoresTemporales Inicio");
	// Object value = null;
	// try {
	// // Locale locale =
	// // FacesContext.getCurrentInstance().getViewRoot().getLocale();
	// // Thu Dec 05 00:00:00 CET 2013
	// // jueves 5 de diciembre de 2013 11H37' CET
	// SimpleDateFormat sdf = new SimpleDateFormat(
	// "EEE MMM dd HH:mm:ss z yyyy", Locale.US);
	// value = sdf.parse(fv.toString());
	// if (tipo.equals(Date.class)) {
	// value = sdf.parse(fv.toString());
	// } else if (tipo.equals(Timestamp.class)) {
	// value = new Timestamp(sdf.parse(fv.toString()).getTime());
	// }
	// } catch (Exception ex) {
	// log.error("PaginacionDataModel.paresearValoresTemporales");
	// }
	// log.debug("PaginacionDataModel.paresearValoresTemporales Fin -" + value
	// + "-");
	// return value;
	// }

	protected abstract T find(Object rowKey);

	protected abstract Object getId(T t);

	protected void initCriterio(final Criterio criterio) {
	};

	protected List<T> getData() {
		return data;
	}

	public Criterio getCriteriosIniciales() {
		return criteriosIniciales;
	}

	public void setCriteriosIniciales(Criterio criteriosIniciales) {
		data = null;
		this.criteriosIniciales = criteriosIniciales;
	}

	public Criterio getCriterio() {
		return criterio;
	}
}