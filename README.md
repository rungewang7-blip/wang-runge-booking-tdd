 
Compile: 
javac -d out -cp junit.jar src/main/java/*.java src/test/java/*.java 
 
Run tests: 
java -jar junit.jar execute --class-path out --scan-class-path 
