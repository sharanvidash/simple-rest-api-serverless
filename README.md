This repo serves as a SAM based Spring boot application to serve basic API requests through API GW HTTP proxy and lambda.


Pre-requistes To deploy:
1. Configure AWS credentials for your AWS account in CLI.
2. Install SAM CLI

Steps:
1. Build sam template
   ```
   sam build
   ```
2. Do a guided sam deploy to set your own stack name
   ```
   sam deploy --guided
   ```
