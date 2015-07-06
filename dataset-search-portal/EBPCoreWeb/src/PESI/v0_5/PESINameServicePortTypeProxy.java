package PESI.v0_5;

public class PESINameServicePortTypeProxy implements PESI.v0_5.PESINameServicePortType {
  private String _endpoint = null;
  private PESI.v0_5.PESINameServicePortType pESINameServicePortType = null;
  
  public PESINameServicePortTypeProxy() {
    _initPESINameServicePortTypeProxy();
  }
  
  public PESINameServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPESINameServicePortTypeProxy();
  }
  
  private void _initPESINameServicePortTypeProxy() {
    try {
      pESINameServicePortType = (new PESI.v0_5.PESINameServiceLocator()).getPESINameServicePort();
      if (pESINameServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pESINameServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pESINameServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (pESINameServicePortType != null)
      ((javax.xml.rpc.Stub)pESINameServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public PESI.v0_5.PESINameServicePortType getPESINameServicePortType() {
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType;
  }
  
  public java.lang.String getGUID(java.lang.String scientificname) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getGUID(scientificname);
  }
  
  public PESI.v0_5.PESIRecord[] getPESIRecords(java.lang.String scientificname, boolean like) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESIRecords(scientificname, like);
  }
  
  public java.lang.String getPESINameByGUID(java.lang.String GUID) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESINameByGUID(GUID);
  }
  
  public PESI.v0_5.PESIRecord getPESIRecordByGUID(java.lang.String GUID) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESIRecordByGUID(GUID);
  }
  
  public PESI.v0_5.PESIRecord[] getPESIRecordsByVernacular(java.lang.String vernacular) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESIRecordsByVernacular(vernacular);
  }
  
  public PESI.v0_5.Vernacular[] getPESIVernacularsByGUID(java.lang.String GUID) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESIVernacularsByGUID(GUID);
  }
  
  public PESI.v0_5.PESIRecord[] matchTaxon(java.lang.String scientificname) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.matchTaxon(scientificname);
  }
  
  public PESI.v0_5.PESIRecord[][] matchTaxa(java.lang.String[] scientificnames) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.matchTaxa(scientificnames);
  }
  
  public PESI.v0_5.PESIRecord[] getPESISynonymsByGUID(java.lang.String GUID) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESISynonymsByGUID(GUID);
  }
  
  public PESI.v0_5.Distribution[] getPESIDistributionsByGUID(java.lang.String GUID) throws java.rmi.RemoteException{
    if (pESINameServicePortType == null)
      _initPESINameServicePortTypeProxy();
    return pESINameServicePortType.getPESIDistributionsByGUID(GUID);
  }
  
  
}