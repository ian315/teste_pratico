# Use imagem do JDK 21
FROM eclipse-temurin:21-jdk as builder

# Cria diretório no container
WORKDIR /app

# Copia arquivos do projeto
COPY . .

# Empacota o projeto
RUN ./mvnw clean package -DskipTests

# Nova imagem apenas com o JAR
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o JAR gerado
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta da aplicação Spring (ajuste se necessário)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]