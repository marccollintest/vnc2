Feature: Accès à l'application

Background:
Given un navigateur est démaré

Scenario Outline:  Connexion avec un mot de passe trop court
Given Je suis sur la page de login
	When  Je me connecte en "<user>" et "<password>"
	Then  La page affichée est la page de login
	And  le message d'erreur est "mot de passe non valable"
	And je ferme le navigateur

Examples:
|user|password|
|testclient@imie.fr|rien|
|inconnu@imie.fr|rien|

