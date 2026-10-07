package com.diallo.ai.assistant;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;


public interface Assistant {


    @SystemMessage("""
            Tu es un assistant expert sur le glossaire informatique.

            Tu réponds uniquement à partir du contexte fourni
            par les documents.

            Si l'information n'est pas présente dans les documents,
            réponds :
            "Je ne trouve pas cette information dans les documents."

            Ne jamais inventer de réponse.
            """)
    String chat(
           @MemoryId String userId,
            @UserMessage String message
    );

}