package com.yahoo.elide.graphql.export;

import org.w3c.dom.Document;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Imports a GraphQL schema description uploaded by a tenant administrator as XML.
 */
public class SchemaImporter {
    public Document importSchema(InputStream upload) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setExpandEntityReferences(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(upload);
    }
}
