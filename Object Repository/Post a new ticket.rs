<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Post a new ticket</name>
   <tag></tag>
   <elementGuidId>4e9d7882-5e75-4ae8-86c8-e0a92efac1d0</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;fields\&quot;:{\n      \&quot;project\&quot;: { \&quot;key\&quot;: \&quot;${project}\&quot;}, \n      \&quot;issuetype\&quot;: {\&quot;name\&quot;: \&quot;${issuetype}\&quot;},\n      \&quot;summary\&quot;: \&quot;${summary}\&quot;\n\t}  \n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Basic ZGVtbzpkZW1vMTIz</value>
   </httpHeaderProperties>
   <katalonVersion>7.7.2</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.serverURL}/rest/api/2/issue/</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'KT'</defaultValue>
      <description></description>
      <id>e689ebfd-ba7f-49b7-ae6b-f29b3c7ba8f4</id>
      <masked>false</masked>
      <name>project</name>
   </variables>
   <variables>
      <defaultValue>'Task'</defaultValue>
      <description></description>
      <id>823f557f-985c-4b31-9e78-53c5dababa84</id>
      <masked>false</masked>
      <name>issuetype</name>
   </variables>
   <variables>
      <defaultValue>'default summary'</defaultValue>
      <description></description>
      <id>7e55aff8-8122-41bb-aae4-63e5a76c90e0</id>
      <masked>false</masked>
      <name>summary</name>
   </variables>
   <variables>
      <defaultValue>201</defaultValue>
      <description></description>
      <id>3e5c9e53-e002-45a3-8a6a-38b185e9cca7</id>
      <masked>false</masked>
      <name>expectedStatus</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

assert response.getStatusCode() == WSResponseManager.getInstance().getCurrentRequest().getVariables().expectedStatus</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
