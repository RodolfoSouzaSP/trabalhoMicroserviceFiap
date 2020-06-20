PR�TICA DE CONCLUS�O DA DISCIPLINA
MICROSERVICES ARCHITECTURE / API / CONTAINERS

MARCIO ROBERTO SANTOS DE LIMA � RM 336494
RODOLFO TOM� DE SOUZA � RM 336344
SIDNEI APARECIDO DOS REIS � RM 335312
VITOR ROBERTO DA SILVA � RM 336199


INSTRU��ES

Utilizamos o case do NETFLIX para fazermos nossa implementa��o.

O projeto foi desenvolvido em JAVA e contempla os seguintes projetos:

catalogo-service: Contempla os servi�os do cat�logo de filmes:
	Este servi�o est� configurado para rodar na porta 8080 e utiliza um banco de dados do MYSQL 
		catalogo -> PRODU��O
		catalogo-hom -> HOMOLOGA��O
		catalogo-dev -> DESENVOLVIMENTO


chamadotecnico-service: Contempla os servi�os do chamado t�cnico:
	Este servi�o est� configurado para rodar na porta 8090 e utiliza banco de dados MYSQL:
		chamado -> PRODU��O
		chamado-hom -> HOMOLOGA��O
		chamado-dev -> DESENVOLVIMENTO


filmeassistido-service: Contempla os servi�os do filme assistido:
	Este servi�o est� configurado para rodar na porta 8085 e utiliza banco de dados MYSQL:
		filme -> PRODU��O
		filme-hom -> HOMOLOGA��O
		filme-dev -> DESENVOLVIMENTO

eurekaserver: (porta: 8761)

ConfigServer: (porta: 8888)

Foram trabalhados os conceitos:
	* Gerenciamento de configura��o
	* Resili�ncia
	* Service-discovery
	* Data-Management
	* Swagger

O c�digo fonte pode ser baixado no GITHUB: https://github.com/vroberto83/trabalhoMicroserviceFiap/tree/v1

As imagens pode ser vistas no arquivo instru��es.docx
