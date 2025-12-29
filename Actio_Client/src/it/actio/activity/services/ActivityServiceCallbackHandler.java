
/**
 * ActivityServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */

    package it.actio.activity.services;

    /**
     *  ActivityServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ActivityServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ActivityServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ActivityServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for accettaIscrizione method
            * override this method for handling normal response from accettaIscrizione operation
            */
           public void receiveResultaccettaIscrizione(
                    it.actio.activity.services.ActivityServiceStub.AccettaIscrizioneResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from accettaIscrizione operation
           */
            public void receiveErroraccettaIscrizione(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registrazioneAttivita method
            * override this method for handling normal response from registrazioneAttivita operation
            */
           public void receiveResultregistrazioneAttivita(
                    it.actio.activity.services.ActivityServiceStub.RegistrazioneAttivitaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registrazioneAttivita operation
           */
            public void receiveErrorregistrazioneAttivita(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAttivita method
            * override this method for handling normal response from getAttivita operation
            */
           public void receiveResultgetAttivita(
                    it.actio.activity.services.ActivityServiceStub.GetAttivitaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAttivita operation
           */
            public void receiveErrorgetAttivita(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCorso method
            * override this method for handling normal response from getCorso operation
            */
           public void receiveResultgetCorso(
                    it.actio.activity.services.ActivityServiceStub.GetCorsoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCorso operation
           */
            public void receiveErrorgetCorso(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCorsiForniti method
            * override this method for handling normal response from getCorsiForniti operation
            */
           public void receiveResultgetCorsiForniti(
                    it.actio.activity.services.ActivityServiceStub.GetCorsiFornitiResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCorsiForniti operation
           */
            public void receiveErrorgetCorsiForniti(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_Iscrizioni_ConNomePersona_Attivita method
            * override this method for handling normal response from get_Iscrizioni_ConNomePersona_Attivita operation
            */
           public void receiveResultget_Iscrizioni_ConNomePersona_Attivita(
                    it.actio.activity.services.ActivityServiceStub.Get_Iscrizioni_ConNomePersona_AttivitaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_Iscrizioni_ConNomePersona_Attivita operation
           */
            public void receiveErrorget_Iscrizioni_ConNomePersona_Attivita(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIscritti_conDatafine method
            * override this method for handling normal response from getIscritti_conDatafine operation
            */
           public void receiveResultgetIscritti_conDatafine(
                    it.actio.activity.services.ActivityServiceStub.GetIscritti_conDatafineResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIscritti_conDatafine operation
           */
            public void receiveErrorgetIscritti_conDatafine(java.lang.Exception e) {
            }
                


    }
    