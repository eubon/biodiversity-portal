/**
 * Taxonomic Record.java
 *
 */

package com.ibm.gbs.eubon.taxonomic;

public class TaxRecord  implements java.io.Serializable {
    private java.lang.String GUID;

    private java.lang.String url;
    
    private java.lang.String GUIDurl;

    private java.lang.String scientificname;

    private java.lang.String authority;

    private java.lang.String rank;

    private java.lang.String status;

    private java.lang.String valid_guid;

    private java.lang.String valid_name;

    private java.lang.String valid_authority;

    private java.lang.String kingdom;

    private java.lang.String phylum;

    private java.lang.String _class;

    private java.lang.String order;

    private java.lang.String family;

    private java.lang.String genus;

    private java.lang.String citation;

    private java.lang.String match_type;

    public TaxRecord() {
    }

    public TaxRecord(
           java.lang.String GUID,
           java.lang.String url,
           java.lang.String scientificname,
           java.lang.String authority,
           java.lang.String rank,
           java.lang.String status,
           java.lang.String valid_guid,
           java.lang.String valid_name,
           java.lang.String valid_authority,
           java.lang.String kingdom,
           java.lang.String phylum,
           java.lang.String _class,
           java.lang.String order,
           java.lang.String family,
           java.lang.String genus,
           java.lang.String citation,
           java.lang.String match_type) {
           this.GUID = GUID;
           this.url = url;
           this.scientificname = scientificname;
           this.authority = authority;
           this.rank = rank;
           this.status = status;
           this.valid_guid = valid_guid;
           this.valid_name = valid_name;
           this.valid_authority = valid_authority;
           this.kingdom = kingdom;
           this.phylum = phylum;
           this._class = _class;
           this.order = order;
           this.family = family;
           this.genus = genus;
           this.citation = citation;
           this.match_type = match_type;
    }


    

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxRecord)) return false;
        TaxRecord other = (TaxRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.GUID==null && other.getGUID()==null) || 
             (this.GUID!=null &&
              this.GUID.equals(other.getGUID()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.scientificname==null && other.getScientificname()==null) || 
             (this.scientificname!=null &&
              this.scientificname.equals(other.getScientificname()))) &&
            ((this.authority==null && other.getAuthority()==null) || 
             (this.authority!=null &&
              this.authority.equals(other.getAuthority()))) &&
            ((this.rank==null && other.getRank()==null) || 
             (this.rank!=null &&
              this.rank.equals(other.getRank()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.valid_guid==null && other.getValid_guid()==null) || 
             (this.valid_guid!=null &&
              this.valid_guid.equals(other.getValid_guid()))) &&
            ((this.valid_name==null && other.getValid_name()==null) || 
             (this.valid_name!=null &&
              this.valid_name.equals(other.getValid_name()))) &&
            ((this.valid_authority==null && other.getValid_authority()==null) || 
             (this.valid_authority!=null &&
              this.valid_authority.equals(other.getValid_authority()))) &&
            ((this.kingdom==null && other.getKingdom()==null) || 
             (this.kingdom!=null &&
              this.kingdom.equals(other.getKingdom()))) &&
            ((this.phylum==null && other.getPhylum()==null) || 
             (this.phylum!=null &&
              this.phylum.equals(other.getPhylum()))) &&
            ((this._class==null && other.get_class()==null) || 
             (this._class!=null &&
              this._class.equals(other.get_class()))) &&
            ((this.order==null && other.getOrder()==null) || 
             (this.order!=null &&
              this.order.equals(other.getOrder()))) &&
            ((this.family==null && other.getFamily()==null) || 
             (this.family!=null &&
              this.family.equals(other.getFamily()))) &&
            ((this.genus==null && other.getGenus()==null) || 
             (this.genus!=null &&
              this.genus.equals(other.getGenus()))) &&
            ((this.citation==null && other.getCitation()==null) || 
             (this.citation!=null &&
              this.citation.equals(other.getCitation()))) &&
            ((this.match_type==null && other.getMatch_type()==null) || 
             (this.match_type!=null &&
              this.match_type.equals(other.getMatch_type())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGUID() != null) {
            _hashCode += getGUID().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getScientificname() != null) {
            _hashCode += getScientificname().hashCode();
        }
        if (getAuthority() != null) {
            _hashCode += getAuthority().hashCode();
        }
        if (getRank() != null) {
            _hashCode += getRank().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getValid_guid() != null) {
            _hashCode += getValid_guid().hashCode();
        }
        if (getValid_name() != null) {
            _hashCode += getValid_name().hashCode();
        }
        if (getValid_authority() != null) {
            _hashCode += getValid_authority().hashCode();
        }
        if (getKingdom() != null) {
            _hashCode += getKingdom().hashCode();
        }
        if (getPhylum() != null) {
            _hashCode += getPhylum().hashCode();
        }
        if (get_class() != null) {
            _hashCode += get_class().hashCode();
        }
        if (getOrder() != null) {
            _hashCode += getOrder().hashCode();
        }
        if (getFamily() != null) {
            _hashCode += getFamily().hashCode();
        }
        if (getGenus() != null) {
            _hashCode += getGenus().hashCode();
        }
        if (getCitation() != null) {
            _hashCode += getCitation().hashCode();
        }
        if (getMatch_type() != null) {
            _hashCode += getMatch_type().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

	public java.lang.String getGUID() {
		return GUID;
	}

	public void setGUID(java.lang.String gUID) {
		GUID = gUID;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getScientificname() {
		return scientificname;
	}

	public void setScientificname(java.lang.String scientificname) {
		this.scientificname = scientificname;
	}

	public java.lang.String getAuthority() {
		return authority;
	}

	public void setAuthority(java.lang.String authority) {
		this.authority = authority;
	}

	public java.lang.String getRank() {
		return rank;
	}

	public void setRank(java.lang.String rank) {
		this.rank = rank;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getValid_guid() {
		return valid_guid;
	}

	public void setValid_guid(java.lang.String valid_guid) {
		this.valid_guid = valid_guid;
	}

	public java.lang.String getValid_name() {
		return valid_name;
	}

	public void setValid_name(java.lang.String valid_name) {
		this.valid_name = valid_name;
	}

	public java.lang.String getValid_authority() {
		return valid_authority;
	}

	public void setValid_authority(java.lang.String valid_authority) {
		this.valid_authority = valid_authority;
	}

	public java.lang.String getKingdom() {
		return kingdom;
	}

	public void setKingdom(java.lang.String kingdom) {
		this.kingdom = kingdom;
	}

	public java.lang.String getPhylum() {
		return phylum;
	}

	public void setPhylum(java.lang.String phylum) {
		this.phylum = phylum;
	}

	public java.lang.String getOrder() {
		return order;
	}

	public void setOrder(java.lang.String order) {
		this.order = order;
	}

	public java.lang.String getFamily() {
		return family;
	}

	public void setFamily(java.lang.String family) {
		this.family = family;
	}

	public java.lang.String getGenus() {
		return genus;
	}

	public void setGenus(java.lang.String genus) {
		this.genus = genus;
	}

	public java.lang.String getCitation() {
		return citation;
	}

	public void setCitation(java.lang.String citation) {
		this.citation = citation;
	}

	public java.lang.String getMatch_type() {
		return match_type;
	}

	public void setMatch_type(java.lang.String match_type) {
		this.match_type = match_type;
	}

	public java.lang.String get_class() {
		return _class;
	}

	public void set_class(java.lang.String _class) {
		this._class = _class;
	}

	public java.lang.String getGUIDurl() {
		return GUIDurl;
	}

	public void setGUIDurl(java.lang.String gUIDurl) {
		GUIDurl = gUIDurl;
	}

    
}
