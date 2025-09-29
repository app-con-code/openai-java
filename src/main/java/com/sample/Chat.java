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