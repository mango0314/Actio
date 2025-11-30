
/**
 * UserServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */

    package it.actio.services;

    /**
     *  UserServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UserServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UserServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UserServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getCorso_conAttivita method
            * override this method for handling normal response from getCorso_conAttivita operation
            */
           public void receiveResultgetCorso_conAttivita(
                    it.actio.services.UserServiceStub.GetCorso_conAttivitaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCorso_conAttivita operation
           */
            public void receiveErrorgetCorso_conAttivita(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCorsi_conPostiRimasti method
            * override this method for handling normal response from getCorsi_conPostiRimasti operation
            */
           public void receiveResultgetCorsi_conPostiRimasti(
                    it.actio.services.UserServiceStub.GetCorsi_conPostiRimastiResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCorsi_conPostiRimasti operation
           */
            public void receiveErrorgetCorsi_conPostiRimasti(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCorsiSeguiti method
            * override this method for handling normal response from getCorsiSeguiti operation
            */
           public void receiveResultgetCorsiSeguiti(
                    it.actio.services.UserServiceStub.GetCorsiSeguitiResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCorsiSeguiti operation
           */
            public void receiveErrorgetCorsiSeguiti(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cercaCorsi_conPostiRimasti method
            * override this method for handling normal response from cercaCorsi_conPostiRimasti operation
            */
           public void receiveResultcercaCorsi_conPostiRimasti(
                    it.actio.services.UserServiceStub.CercaCorsi_conPostiRimastiResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cercaCorsi_conPostiRimasti operation
           */
            public void receiveErrorcercaCorsi_conPostiRimasti(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOrari method
            * override this method for handling normal response from getOrari operation
            */
           public void receiveResultgetOrari(
                    it.actio.services.UserServiceStub.GetOrariResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrari operation
           */
            public void receiveErrorgetOrari(java.lang.Exception e) {
            }
                


    }
    