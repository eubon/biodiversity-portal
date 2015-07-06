package com.ibm.gbs.tramitator.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.jpa.util.Criterio;

@Stateless
public class UtilDAOImpl implements UtilDAO {

	@PersistenceContext(unitName="EBPCoreJPA",type=PersistenceContextType.TRANSACTION)
	EntityManager em;

	Logger log = Logger.getLogger(UtilDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#findAll(java.lang.Class,
	 * java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> findAll(Class<T> transferObjectClass,
			String sortColumn, boolean ascending) {

		final String entityName = transferObjectClass.getSimpleName();
		final Query query;

		if (ascending)
			query = em.createQuery("from " + entityName + " e order by e."
					+ sortColumn + " asc");
		else
			query = em.createQuery("from " + entityName + " e order by e."
					+ sortColumn + " desc");
		final List<T> resultList = query.getResultList();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> findAll(Class<T> transferObjectClass) {

		final String entityName = transferObjectClass.getSimpleName();
		final Query query = em.createQuery("from " + entityName);
		final List<T> resultList = query.getResultList();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#find(java.lang.Class,
	 * java.lang.Object)
	 */
	public <T> T find(Class<T> transferObjectClass, Object id) {
		return em.find(transferObjectClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#find(java.lang.Class,
	 * java.lang.Long)
	 */
	public <T> T find(Class<T> transferObjectClass, Long id) {
		return em.find(transferObjectClass, id);
	}

	private Query createQuery(String query, Map<String, Object> params) {
		Query qQuery = em.createQuery(query);
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				qQuery.setParameter(key, value);
			}
		}
		return qQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.gbs.tramitator.dao.UtilDAO#findAllAndFilterLike(java.lang.Class,
	 * java.util.Map, java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAllAndFilterLike(Class<T> transferObjectClass,
			Map<String, Object> params, String sortColumn, boolean ascending) {

		final String entityName = transferObjectClass.getSimpleName();
		StringBuffer sbQuery = new StringBuffer("from ").append(entityName)
				.append(" e");
		final Query query;
		Map<String, Object> params2 = new Hashtable<String, Object>();
		boolean first = true;
		if ((params != null) && (params.size() > 0)) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (first)
					sbQuery.append(" where e.").append(key).append(" like :")
							.append(key);
				else
					sbQuery.append(" and e.").append(key).append(" like :")
							.append(key);

				first = false;
				params2.put(key, params.get(key) + "%");
			}
		}
		if (ascending)
			sbQuery.append(" order by e." + sortColumn + " asc");
		else
			sbQuery.append(" order by e." + sortColumn + " desc");

		query = createQuery(sbQuery.toString(), params2);

		final List<T> resultList = query.getResultList();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#findAllByQuery(java.lang.String,
	 * java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAllByQuery(String query, Map<String, Object> params) {
		Query qQuery = createQuery(query, params);
		return qQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.gbs.tramitator.dao.UtilDAO#findSingleByQuery(java.lang.String,
	 * java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> T findSingleByQuery(String query, Map<String, Object> params) {

		Query qQuery = createQuery(query, params);
		Object obj = null;

		try {
			obj = qQuery.getSingleResult();
		} catch (NoResultException e) {
			e.getStackTrace();
		}

		return (T) obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#find_s(java.lang.Class,
	 * java.lang.String)
	 */
	public <T> T find_s(Class<T> transferObjectClass, String id) {
		return em.find(transferObjectClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.gbs.tramitator.dao.UtilDAO#updateCollection(java.util.Collection)
	 */
	public <T> void updateCollection(Collection<T> obj) {
		for (Object o : obj)
			em.merge(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#update(java.lang.Object)
	 */
	public Object update(Object obj) {
		Object o = em.merge(obj);
		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.gbs.tramitator.dao.UtilDAO#remove(java.lang.Class,
	 * java.lang.Object)
	 */
	public <T> void remove(Class<T> tipo, Object id) {
		Object obj = em.find(tipo, id);
		em.remove(obj);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> List<T> listadoPaginacion(Class<T> clase, Criterio criterio) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> q = cb.createQuery(clase);
			Root<T> c = q.from(clase);

			Map<String, String> orders = criterio.getOrders();

			order(cb, q, c, orders);

			Map<String, ParameterExpression> params = new HashMap<String, ParameterExpression>();
			Map<String, ParameterExpression> paramsLe = new HashMap<String, ParameterExpression>();

			Predicate[] filter = filter(PredicateFactory.EQ_FACTORY, cb, q, c,
					criterio.getFiltros(), params);
			Predicate[] filterNe = filter(PredicateFactory.NEQ_FACTORY, cb, q, c,
					criterio.getFiltrosNe(), params);
			Predicate[] filterLike = filter(PredicateFactory.LIKE_FACTORY, cb,
					q, c, criterio.getFiltrosLike(), params);
			Predicate[] filterGe = filter(PredicateFactory.GE_FACTORY, cb, q,
					c, criterio.getFiltrosGe(), params);
			Predicate[] filterLe = filter(PredicateFactory.LE_FACTORY, cb, q,
					c, criterio.getFiltrosLe(), paramsLe);
			Predicate[] conditions = condiciones(cb, q, c,
					criterio.getCondiciones(), params);

			Predicate predicate = cb.and(cb.and(filter), cb.and(filterNe), cb.and(filterGe),
					cb.and(filterLe), cb.and(filterLike), cb.and(conditions));
			q.where(predicate);

			CriteriaQuery cq = q.select(c);

			Query query = em.createQuery(cq);

			if (criterio.getNum() > 0) {
				query.setFirstResult(criterio.getPrimero());
				query.setMaxResults(criterio.getNum());
			}

			setParams(params, query, criterio.getFiltros().entrySet());
			setParams(params, query, criterio.getFiltrosNe().entrySet());
			setParams(params, query, criterio.getFiltrosGe().entrySet());
			setParams(paramsLe, query, criterio.getFiltrosLe().entrySet());

			Set<Entry<String, Object>> entrySet = criterio.getFiltrosLike()
					.entrySet();
			if (entrySet != null)
				for (Entry<String, Object> par : entrySet) {
					query.setParameter(params.get(par.getKey()),
							"%" + par.getValue() + "%");
				}

			List<T> resultList = query.getResultList();
			return resultList;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setParams(Map<String, ParameterExpression> params,
			Query query, Set<Entry<String, Object>> paramSet) {
		if (paramSet != null)
			for (Entry<String, Object> par : paramSet) {
				if (par.getValue() != null && params.containsKey(par.getKey())) {
					if (par.getValue() instanceof List) {
						List valueList = (List) par.getValue();
						if (valueList.contains(null) && valueList.size() == 1) {
							continue;
						}
					}
					query.setParameter(params.get(par.getKey()), par.getValue());
				}
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Predicate[] condiciones(CriteriaBuilder cb, CriteriaQuery q,
			Root c, List<String> condiciones,
			Map<String, ParameterExpression> params) {
		Predicate[] listWhere = null;
		if (condiciones != null) {
			listWhere = new Predicate[condiciones.size()];
			Iterator<String> it = condiciones.iterator();

			for (int j = 0; it.hasNext(); j++) {
				String condicion = it.next();

				int p = condicion.indexOf(' ');

				String campo = condicion.substring(0, p);
				String pred = condicion.substring(p);

				String[] split = campo.split("\\.");

				From nodo = c;
				for (int i = 0; i < split.length - 1; i++)
					nodo = nodo.join(split[i]);

				String extremoCampo = split[split.length - 1];

				Predicate equal = null;
				final String predTrim = pred.trim();
				if (predTrim.equalsIgnoreCase("is null")) {
					equal = cb.isNull(nodo.get(extremoCampo));
				} else if (predTrim.equalsIgnoreCase("is empty")) {
					equal = cb.isEmpty(nodo.get(extremoCampo));
				} else if (predTrim.equalsIgnoreCase("is not null") || predTrim.equalsIgnoreCase("not is null")) {
					equal = cb.isNotNull(nodo.get(extremoCampo));
				}

				if (pred != null)
					listWhere[j] = equal;
			}
		} else {
			listWhere = new Predicate[0];
		}
		return listWhere;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private Predicate[] filter(PredicateFactory predicateFactory,
			CriteriaBuilder cb, CriteriaQuery q, Root c,
			Map<String, Object> filters, Map<String, ParameterExpression> params) {

		List<Predicate> arrayListWhere = new ArrayList<Predicate>();

		Predicate[] listWhere = null;
		if (filters != null) {
			// listWhere = new Predicate[filters.size()];
			Iterator<Entry<String, Object>> it = filters.entrySet().iterator();

			for (int j = 0; it.hasNext(); j++) {
				Entry<String, Object> order = it.next();

				if (order.getValue() != null) {
					Order ord;
					String campo = order.getKey();

					String[] split = campo.split("\\.");

					From nodo = c;
					for (int i = 0; i < split.length - 1; i++)
						nodo = nodo.join(split[i]);

					String extremoCampo = split[split.length - 1];
					Expression<String> exp = nodo.get(extremoCampo);

					// Se supone que solo se insertarán Objetos de tipo List si
					// se realizan búsquedas de tipo equal.
					if (order.getValue() instanceof List) {
						List value = new ArrayList();
						value.addAll((List) order.getValue());
						
						boolean hasNull = false;
						if (value.contains(null)) {
							hasNull = true;
							value.remove(null);
						}
						Predicate p = exp.in(value.toArray());
						if (predicateFactory.equals(PredicateFactory.NEQ_FACTORY)) {
							p = cb.not(exp.in(value.toArray()));
						}
						if (hasNull) {
							Predicate equal = cb.isNull(nodo.get(extremoCampo));
							if (value.size() > 0){
								arrayListWhere.add(cb.or(p, equal));
							} else {
								arrayListWhere.add(equal);
							}
						} else {
							arrayListWhere.add(p);
						}
					} else {
						ParameterExpression param = cb.parameter(order
								.getValue().getClass());
						Predicate p = predicateFactory.createPredicate(cb,
								nodo, extremoCampo, param);
						arrayListWhere.add(p);
						params.put(campo, param);
					}
				}
			}
			listWhere = new Predicate[arrayListWhere.size()];
			listWhere = arrayListWhere.toArray(listWhere);
		} else {
			listWhere = new Predicate[0];
		}
		return listWhere;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void order(CriteriaBuilder cb, CriteriaQuery q, Root c,
			Map<String, String> orders) {
		if (orders != null) {
//			Iterator<Entry<String, String>> it = orders.entrySet().iterator();

			List<Order> listOrder = new ArrayList<Order>();

			for (Entry<String, String> order : orders.entrySet()) {
				
//			while (it.hasNext()) {
//				Entry<String, String> order = it.next();

				Order ord = null;
				String campo = order.getKey();

				String[] split = campo.split("\\.");

				From nodo = c;
//				root.get("id").get("edition.number");
//				if (split.length>1) {
//					nodo = (From) nodo.get(split[0]).get(split[1]);
//				}else{
					for (int i = 0; i < split.length - 1; i++){
						nodo = nodo.join(split[i]);
//						nodo = nodo.join(split[i], JoinType.LEFT);
					 }
//				}
					
					Path path = null;
				Object aux = nodo.get(split[split.length - 1]);
				if (aux instanceof Path) {
					path = (Path) aux;
				}
				
				if ("ASC".equalsIgnoreCase(order.getValue())) {
					ord = cb.asc(path);
				} else {
					ord = cb.desc(path);
				}

				listOrder.add(ord);
			}
			q.orderBy(listOrder);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int countPaginacion(Class clase, Criterio criterio) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery q = cb.createQuery(clase);
			Root c = q.from(clase);

			Map<String, String> orders = criterio.getOrders();

			order(cb, q, c, orders);

			Map<String, ParameterExpression> params = new HashMap<String, ParameterExpression>();
			Map<String, ParameterExpression> paramsLe = new HashMap<String, ParameterExpression>();

			Predicate[] filter = filter(PredicateFactory.EQ_FACTORY, cb, q, c,
					criterio.getFiltros(), params);
			Predicate[] filterNe = filter(PredicateFactory.NEQ_FACTORY, cb, q, c,
					criterio.getFiltrosNe(), params);
			Predicate[] filterLike = filter(PredicateFactory.LIKE_FACTORY, cb,
					q, c, criterio.getFiltrosLike(), params);
			Predicate[] filterGe = filter(PredicateFactory.GE_FACTORY, cb, q,
					c, criterio.getFiltrosGe(), params);
			Predicate[] filterLe = filter(PredicateFactory.LE_FACTORY, cb, q,
					c, criterio.getFiltrosLe(), paramsLe);
			Predicate[] conditions = condiciones(cb, q, c,
					criterio.getCondiciones(), params);

			Predicate predicate = cb.and(cb.and(filter), cb.and(filterNe), cb.and(filterGe),
					cb.and(filterLe), cb.and(filterLike), cb.and(conditions));
			q.where(predicate);

			CriteriaQuery cq = q.select(cb.count(c));

			Query query = em.createQuery(cq);

			setParams(params, query, criterio.getFiltros().entrySet());
			setParams(params, query, criterio.getFiltrosNe().entrySet());
			setParams(params, query, criterio.getFiltrosGe().entrySet());
			setParams(paramsLe, query, criterio.getFiltrosLe().entrySet());

			Set<Entry<String, Object>> entrySet = criterio.getFiltrosLike()
					.entrySet();
			for (Entry<String, Object> par : entrySet) {
				query.setParameter(params.get(par.getKey()),
						"%" + par.getValue() + "%");
			}

			List resultList = query.getResultList();
			return ((Long) resultList.get(0)).intValue();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	interface PredicateFactory {

		PredicateFactory EQ_FACTORY = new PredicateFactory() {
			@SuppressWarnings("rawtypes")
			public Predicate createPredicate(CriteriaBuilder cb, From nodo,
					String extremoCampo, ParameterExpression param) {
				return cb.equal(nodo.get(extremoCampo), param);
			}
		};
		PredicateFactory NEQ_FACTORY = new PredicateFactory() {
			@SuppressWarnings("rawtypes")
			public Predicate createPredicate(CriteriaBuilder cb, From nodo,
					String extremoCampo, ParameterExpression param) {
				return cb.notEqual(nodo.get(extremoCampo), param);
			}
		};
		PredicateFactory GE_FACTORY = new PredicateFactory() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Predicate createPredicate(CriteriaBuilder cb, From nodo,
					String extremoCampo, ParameterExpression param) {
				return cb.greaterThanOrEqualTo(nodo.get(extremoCampo), param);
			}
		};
		PredicateFactory LE_FACTORY = new PredicateFactory() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Predicate createPredicate(CriteriaBuilder cb, From nodo,
					String extremoCampo, ParameterExpression param) {
				return cb.lessThanOrEqualTo(nodo.get(extremoCampo), param);
			}
		};
		PredicateFactory LIKE_FACTORY = new PredicateFactory() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Predicate createPredicate(CriteriaBuilder cb, From nodo,
					String extremoCampo, ParameterExpression param) {
				return cb.like(cb.upper(nodo.get(extremoCampo)),
						cb.upper(param));
			}
		};

		@SuppressWarnings("rawtypes")
		Predicate createPredicate(CriteriaBuilder cb, From nodo,
				String extremoCampo, ParameterExpression param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countPaginacionJPQL(Class clase, Criterio criterio) {
		try {
			String sWhere = "";
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			HashMap<String, Object> paramLike = new HashMap<String, Object>();
			sWhere = filterJPQL(sWhere, criterio.getFiltros(), parametros, "=");
			sWhere = filterJPQL(sWhere, criterio.getFiltrosNe(), parametros, "<>");
			sWhere = filterJPQL(sWhere, criterio.getFiltrosGe(), parametros,
					">=");
			sWhere = filterJPQL(sWhere, criterio.getFiltrosLe(), parametros,
					"<=");
			sWhere = filterJPQL(sWhere, criterio.getFiltrosLike(), paramLike,
					"LIKE");
			sWhere = condicionesJPQL(sWhere, criterio.getCondiciones());

			if ("".equals(sWhere.trim())) {
				sWhere = "Select count(*) FROM " + clase.getSimpleName();
			} else {
				sWhere = "Select count(*) FROM " + clase.getSimpleName()
						+ " Where " + sWhere;
			}
			Query query = em.createQuery(sWhere);
			for (Entry<String, Object> parametro : parametros.entrySet()) {
				if (parametro.getValue() != null) {
					query.setParameter(parametro.getKey(), parametro.getValue());
				}
			}

			for (Entry<String, Object> parametro : paramLike.entrySet()) {
				if (parametro.getValue() != null) {
					query.setParameter(parametro.getKey(),
							"%" + parametro.getValue() + "%");
				}
			}
			List resultList = query.getResultList();
			return ((Long) resultList.get(0)).intValue();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String filterJPQL(String sWhere, Map<String, Object> filters,
			HashMap<String, Object> params, String type) {
		if (filters != null) {
			for (Entry<String, Object> order : filters.entrySet()) {
				if (order.getValue() != null) {
					String campo = order.getKey();
					// Para realizar la JPQL query es necesaria la ruta completa
					// del campo
					// String[] split = campo.split("\\.");
					String extremoCampo = campo;
					// for (int i = 0; i < split.length - 1; i++)
					// extremoCampo = split[i];
					int idParam = params.size();
					// Se supone que solo se insertarán Objetos de tipo List si
					// se realizan búsquedas de tipo equal or not equal.
					if (order.getValue() instanceof List) {
						String operator = " IN ";
						if ("<>".equals(type)) {
							operator = " NOT "+operator;
						}
						List value = new ArrayList();
						value.addAll((List) order.getValue());
						if (value.contains(null)) {
							value.remove(null);
							if (value.size() > 0) {
								sWhere = modifyWhereJPQL(sWhere, extremoCampo
										+ operator +" (:list" + idParam + ") OR "+extremoCampo+" IS NULL ");
								params.put("list" + idParam, value);
							} else {
								sWhere = modifyWhereJPQL(sWhere, extremoCampo+" IS NULL ");
							}
						} else {
							sWhere = modifyWhereJPQL(sWhere, extremoCampo
									+ operator +" (:list" + idParam + ")");
							params.put("list" + idParam, value);
						}
					} else {
						sWhere = modifyWhereJPQL(sWhere, extremoCampo + " "
								+ type + " :param" + idParam);
						params.put("param" + idParam, order.getValue());
					}
				}
			}
		}
		return sWhere;
	}

	private String condicionesJPQL(String sWhere, List<String> condiciones) {
		if (condiciones != null) {
			for (String condicion : condiciones) {
				int p = condicion.indexOf(' ');

				String campo = condicion.substring(0, p);
				String pred = condicion.substring(p);

				String[] split = campo.split("\\.");

				String extremoCampo = split[split.length - 1];

				String predTrim = pred.trim();
				sWhere = modifyWhereJPQL(sWhere, extremoCampo + " " + predTrim);
			}
		}
		return sWhere;
	}

	private String modifyWhereJPQL(String sWhere, String whereParcial) {
		if (sWhere.length() > 0) {
			sWhere = sWhere + " AND ";
		}
		sWhere = sWhere + "( " + whereParcial + " )";
		return sWhere;
	}
}
