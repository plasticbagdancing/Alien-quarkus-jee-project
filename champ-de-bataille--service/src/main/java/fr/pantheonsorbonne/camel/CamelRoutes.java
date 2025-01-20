package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /* Configuration des routes pour capter les messages du radar et notifier le Boss.
                Route pour filtrer les messages selon les types d'aliens et les seuils critiques, utilisant un pattern de Message Filter.
        Route pour publier les messages vers les systèmes de soldats et d'arsenal via un Publish-Subscribe. */

    }
}


