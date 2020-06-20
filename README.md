PRÁTICA DE CONCLUSÃO DA DISCIPLINA
MICROSERVICES ARCHITECTURE / API / CONTAINERS

MARCIO ROBERTO SANTOS DE LIMA – RM 336494
RODOLFO TOMÉ DE SOUZA – RM 336344
SIDNEI APARECIDO DOS REIS – RM 335312
VITOR ROBERTO DA SILVA – RM 336199


INSTRUÇÕES

Utilizamos o case do NETFLIX para fazermos nossa implementação.

O projeto foi desenvolvido em JAVA e contempla os seguintes projetos:

catalogo-service: Contempla os serviços do catálogo de filmes:
	Este serviço está configurado para rodar na porta 8080 e utiliza um banco de dados do MYSQL 
		catalogo -> PRODUÇÃO
		catalogo-hom -> HOMOLOGAÇÃO
		catalogo-dev -> DESENVOLVIMENTO


chamadotecnico-service: Contempla os serviços do chamado técnico:
	Este serviço está configurado para rodar na porta 8090 e utiliza banco de dados MYSQL:
		chamado -> PRODUÇÃO
		chamado-hom -> HOMOLOGAÇÃO
		chamado-dev -> DESENVOLVIMENTO


filmeassistido-service: Contempla os serviços do filme assistido:
	Este serviço está configurado para rodar na porta 8085 e utiliza banco de dados MYSQL:
		filme -> PRODUÇÃO
		filme-hom -> HOMOLOGAÇÃO
		filme-dev -> DESENVOLVIMENTO

eurekaserver: (porta: 8761)

ConfigServer: (porta: 8888)

Foram trabalhados os conceitos:
	* Gerenciamento de configuração
	* Resiliência
	* Service-discovery
	* Data-Management
	* Swagger

O código fonte pode ser baixado no GITHUB: https://github.com/vroberto83/trabalhoMicroserviceFiap/tree/v1

As imagens pode ser vistas no arquivo instruções.docx
