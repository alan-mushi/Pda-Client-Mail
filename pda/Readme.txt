Projet de développement de l'application PDA.

Structure du répertoire de développement
----------------------------------------

    projetPDA/		: projet de développement PDA
    |-- Readme.txt	: ce fichier
    |-- ant		: fichiers d'automatisation de ANT
    |-- build       	: fichiers .class et autres (résultant d'une compilation ou génération d'une javaDoc ou d'archives)
    |-- data		: données et images de l'application
    |-- lib		: librairies exterieures (.jar) utilisées par l'application
    |-- server		: exécutable jar d'un serveur de messagerie local
    |-- src		: sources Java
    |   `-- pda     	: paquetage principal de l'application
    |	`-- pdaNetwork	: paquetage de dialogue avec le serveur de messagerie
    `-- ww          	: répertoire de travail du développeur (!! n'existe pas encore après désarchivage !!)

Mode d'emploi
-------------

	1re Utilisation
	---------------

	Vous devez exécuter cette commande une fois pour toute :
	$ cd projetPDA
	$ ant -f ant/build.xml init

	Ensuite, il faut se placer dans le répertoire 'projetPDA/ww'

	En cours de développement
	-------------------------

	L'environnement est contrôlé par un script ANT ; il faut TOUJOURS se placer dans le répertoire 'projetPDA/ww'.

	La commande :
	$ ant -p

	permet d'obtenir la liste des commandes de compilation et de manipulation des sources.

	Lancement du pda
	----------------

	$ ant run

	Lancement du test du client de messagerie
	-----------------------------------------

	1. Lancer le serveur de messagerie en local : ALLER DANS LE REPERTOIRE projetPDA/server et lancer le serveur "$ java -jar pdaServer.jar"

	2. Le serveur est lancé et est à l'écoute de messages en provenance de votre propre machine ("localhost")

	3. Lancement du test du client de messagerie : envois de messages de user1 à user2 (2 comptes déjà créés)

	   DANS LE REPERTOIRE projetPDA/ww, exécuter "$ ant runTestClient"


Serveur de messagerie
---------------------

Un serveur de messagerie commun à TOUS les binômes tournera sur une machine du réseau enseignement.

Le nom de cette machine (communiqué ultérieurement) est à renseigner dans le champ <remoteHost>...</remoteHost> du fichier 
de configuration en projetPDA/data/xml/pdaServer/configClient.xml.

Le numéro du port de communication est 8888.