#!/bin/bash
mvn -f ../service-google/pom.xml clean verify appengine:devserver
