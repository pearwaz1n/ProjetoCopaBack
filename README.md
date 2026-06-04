# Projeto Copa - API de Gestao de Ingressos (Backend)

## Sobre o Projeto
O sistema atua como o backend para uma aplicacao de simulacao de compra e gestao de ingressos, fornecendo os endpoints necessarios para a comunicacao com o frontend.

## Arquitetura e Padroes
O projeto foi estruturado utilizando uma arquitetura em camadas padrao de mercado, garantindo separacao de responsabilidades, seguranca e escalabilidade:
* **Controllers:** Expondo endpoints REST e controlando o trafego HTTP.
* **Services:** Isolando as regras de negocio.
* **Repositories:** Interface de comunicacao direta com o banco de dados via Spring Data JPA.
* **DTOs (Data Transfer Objects) e Mappers:
* 
## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3+** (Web, Data JPA, Validation)
* **Banco de Dados H2** (In-memory, para testes e desenvolvimento agil)
* **Maven** (Gerenciamento de dependencias)
