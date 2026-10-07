package com.diallo.ai.assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ProfileExtractor {

	@SystemMessage("""
			Tu analyses le message d'un utilisateur.

			Retourne uniquement un JSON.

			Aucun commentaire.

			Format :

			{
			  "firstName": null,
			  "lastName": null,
			  "job": null,
			  "experience": null,
			  "city": null
			}

			Si une information est absente laisse null.
			""")
	String extract(@UserMessage String message);

}