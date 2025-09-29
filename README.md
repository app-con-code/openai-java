# OpenAI SDK for Java

Get Started  
Below are example code snippets for a few use cases. For additional information about OpenAI SDK, see full [documentation](https://github.com/openai/openai-java)  and [samples](https://github.com/openai/openai-java) .

1. Authentication using API Key
   For OpenAI API Endpoints, deploy the Model to generate the endpoint URL and an API key to authenticate against the service. In this sample endpoint and key are strings holding the endpoint URL and the API Key.

The API endpoint URL and API key can be found on the Deployments + Endpoint page once the model is deployed.

To create a client with the OpenAI SDK using an API key, initialize the client by passing your API key to the SDK's configuration. This allows you to authenticate and interact with OpenAI's services seamlessly:
```
OpenAIClient client = OpenAIOkHttpClient.builder()
.baseUrl(endpoint)
.credential(AzureApiKeyCredential.create("<your-api-key>"))
.build();

2. Install dependencies
   To install, add this in your maven pom.xml:
   <dependencies>
   <dependency>
   <groupId>com.openai</groupId>
   <artifactId>openai</artifactId>
   <version>1.0.0</version>
   </dependency>
   <dependency>
   <groupId>com.azure</groupId>
   <artifactId>azure-identity</artifactId>
   <version>1.13.3</version>
   </dependency>
   </dependencies>
```

For each of the code snippets below, copy the content into a sample.java file and run as a package, for instance:
```
mvn clean package
mvn exec:java -Dexec.mainClass="com.samples.Chat"
```
3. Run a basic code sample
   This sample demonstrates a basic call to the chat completion API. The call is synchronous.
```
package com.sample;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionMessage;

import com.openai.azure.credential.AzureApiKeyCredential;

public final class Chat {
public static void main(String[] args) {
String apiKey = "<your-api-key>";
String endpoint = "https://gofor-m67pudm0-eastus2.cognitiveservices.azure.com/openai/v1/";
String deploymentName = "gpt-4o";

        OpenAIClient client = OpenAIOkHttpClient.builder()
            .baseUrl(endpoint)
            .credential(AzureApiKeyCredential.create("<your-api-key>"))
            .build();

        ChatCompletionCreateParams createParams = ChatCompletionCreateParams.builder()
            .model(ChatModel.of(deploymentName))
            .addSystemMessage("You are a helpful assistant. You will talk like a pirate.")
            .addUserMessage("Can you help me?")
            .addUserMessage("What's the best way to train a parrot?")
            .build();

        ChatCompletion chatCompletion = client.chat().completions().create(createParams);
        System.out.printf("Model ID=%s is created at %s.%n", chatCompletion.id(), chatCompletion.created());

        for (ChatCompletion.Choice choice : chatCompletion.choices()) {
            ChatCompletionMessage message = choice.message();
            System.out.println("Message:");
            System.out.println(message.content());
        }
    }
}
```
https://pitch-eel-0ef.notion.site/OpenAI-gpt-5-27c0711f396d8086ba40f5b67ea98532?source=copy_link