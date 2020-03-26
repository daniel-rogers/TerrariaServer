# aws

This module contains all of the AWS Lambda code, which is responding to our HTTP Requests and returning responses.

## Modules
There is a Gradle module defined for each Lambda function.

### [startLambda](./startLambda/README.md)
The `startLambda` module includes the request handler for starting the EC2 instance and returning it's public IP.

### [stopLambda](./stopLambda/README.md)
The `stopLambda` module includes the request handler for stopping the EC2 instance. 

### [infoLambda](./infoLambda/README.md)
The `infoLambda` module includes the request handler for getting information about the EC2 instance.