Questions

* Open API Generator - https://github.com/OpenAPITools/openapi-generator

* Run ActiveMq

`docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.14.3`

Credentials: admin/admin

Create two queues: request/response


and run the app

I* Run ibm mq
* 
*

docker run -e LICENSE=accept -e MQ_QMGR_NAME=QM1 -p 1414:1414 -p 9443:9443 ibmcom/mq
