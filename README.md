   
 **Desafio-back-votos** 
    
    
    
 Resumo do Sistema
 
- Solução back-end para gerenciar sessões de votação. 



 Tecnologias utilizadas
* Java 11
* Swagger
* MongoDB
* Docker
* Gradle
* JUnit




 Objetivos concluídos: 


- Cadastrar uma nova pauta; 

- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default); 

- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta); 

- Contabilizar os votos e dar o resultado da votação na pauta. 



 Inicializando o Sistema
 
 
 Requerimentos
 
 * Java 11
 * Docker
 
 
 
 Comandos:
 
 "Executados na raiz do projeto"
 - docker-compose up -d
 - docker-compose down
 - java -jar ./build/libs/src-0.0.1-SNAPSHOT.jar
 
 
 
 Documentação 


- Após rodar o sistema você pode acessar a documentação pela url abaixo onde você encontra endpoints e modelos para 
 requisições.

- http://127.0.0.1:8080/swagger-ui.html#/