#!/bin/bash
clear
# javadoc -d doc --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -charset utf8 -noqualifier all src/*.java
find -name "*.java" > .sources.txt
javac -d bin/ --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls @.sources.txt
java -cp bin/:/usr/share/java/mariadb-java-client.jar:img --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls AppliLib