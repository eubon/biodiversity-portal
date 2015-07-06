/**
 * PESINameServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package PESIv0_5;

public class PESINameServiceLocator extends org.apache.axis.client.Service implements PESIv0_5.PESINameService {

    public PESINameServiceLocator() {
    }


    public PESINameServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PESINameServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PESINameServicePort
    private java.lang.String PESINameServicePort_address = "http://www.eu-nomen.eu/portal/soap.php";

    public java.lang.String getPESINameServicePortAddress() {
        return PESINameServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PESINameServicePortWSDDServiceName = "PESINameServicePort";

    public java.lang.String getPESINameServicePortWSDDServiceName() {
        return PESINameServicePortWSDDServiceName;
    }

    public void setPESINameServicePortWSDDServiceName(java.lang.String name) {
        PESINameServicePortWSDDServiceName = name;
    }

    public PESIv0_5.PESINameServicePortType getPESINameServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PESINameServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPESINameServicePort(endpoint);
    }

    public PESIv0_5.PESINameServicePortType getPESINameServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            PESIv0_5.PESINameServiceBindingStub _stub = new PESIv0_5.PESINameServiceBindingStub(portAddress, this);
            _stub.setPortName(getPESINameServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPESINameServicePortEndpointAddress(java.lang.String address) {
        PESINameServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (PESIv0_5.PESINameServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                PESIv0_5.PESINameServiceBindingStub _stub = new PESIv0_5.PESINameServiceBindingStub(new java.net.URL(PESINameServicePort_address), this);
                _stub.setPortName(getPESINameServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PESINameServicePort".equals(inputPortName)) {
            return getPESINameServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://PESI/v0.5", "PESINameService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://PESI/v0.5", "PESINameServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PESINameServicePort".equals(portName)) {
            setPESINameServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
