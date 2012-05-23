Projet de d�veloppement de l'application PDA.

Structure du r�pertoire de d�veloppement
----------------------------------------

    projetPDA/		: projet de d�veloppement PDA
    |-- Readme.txt	: ce fichier
    |-- ant		: fichiers d'automatisation de ANT
    |-- build       	: fichiers .class et autres (r�sultant d'une compilation ou g�n�ration d'une javaDoc ou d'archives)
    |-- data		: donn�es et images de l'application
    |-- lib		: librairies exterieures (.jar) utilis�es par l'application
    |-- server		: ex�cutable jar d'un serveur de messagerie local
    |-- src		: sources Java
    |   `-- pda     	: paquetage principal de l'application
    |	`-- pdaNetwork	: paquetage de dialogue avec le serveur de messagerie
    `-- ww          	: r�pertoire de travail du d�veloppeur (!! n'existe pas encore apr�s d�sarchivage !!)

Mode d'emploi
-------------

	1re Utilisation
	---------------

	Vous devez ex�cuter cette commande une fois pour toute :
	$ cd projetPDA
	$ ant -f ant/build.xml init

	Ensuite, il faut se placer dans le r�pertoire 'projetPDA/ww'

	En cours de d�veloppement
	-------------------------

	L'environnement est contr�l� par un script ANT ; il faut TOUJOURS se placer dans le r�pertoire 'projetPDA/ww'.

	La commande :
	$ ant -p

	permet d'obtenir la liste des commandes de compilation et de manipulation des sources.

	Lancement du pda
	----------------

	$ ant run

	Lancement du test du client de messagerie
	-----------------------------------------

	1. Lancer le serveur de messagerie en local : ALLER DANS LE REPERTOIRE projetPDA/server et lancer le serveur "$ java -jar pdaServer.jar"

	2. Le serveur est lanc� et est � l'�coute de messages en provenance de votre propre machine ("localhost")

	3. Lancement du test du client de messagerie : envois de messages de user1 � user2 (2 comptes d�j� cr��s)

	   DANS LE REPERTOIRE projetPDA/ww, ex�cuter "$ ant runTestClient"


Serveur de messagerie
---------------------

Un serveur de messagerie commun � TOUS les bin�mes tournera sur une machine du r�seau enseignement.

Le nom de cette machine (communiqu� ult�rieurement) est � renseigner dans le champ <remoteHost>...</remoteHost> du fichier 
de configuration en projetPDA/data/xml/pdaServer/configClient.xml.

Le num�ro du port de communication est 8888.