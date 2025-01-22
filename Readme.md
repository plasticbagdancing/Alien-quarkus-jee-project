# Objectif du système à modéliser
Modéliser un système de gestion pour organiser la défense d'une station spatiale contre des attaques extraterrestres.

## Fonctionnalités
- Détecter les attaques extraterrestres et se défendre efficacement contre celles-ci.
- Gérer les ressources (armes, défense, soins) de manière optimale.

## Scénario : "Colonie Perdue"
Dans un futur proche, une colonie humaine s’est installée sur une planète lointaine, Cybertron, pour exploiter ses ressources. Les tensions montent lorsqu’une race extraterrestre, les Mucucu, revendique la planète comme leur territoire ancestral. Une nuit, la colonie subit une attaque brutale, forçant le commandant Boss à agir et les soldats à se défendre.

## Règles métier
- **R1** : Les Aliens sont générés aléatoirement par type (hostile, allié, opportuniste) et assignés à des salles spécifiques dans le champ de bataille.
- **R2** : Les informations sur les Aliens sont collectées par le champ de bataille et transmises au radar.
- **R3** : Le radar transmet ces informations à Détection, qui doit notifier le Boss dans un délai maximal de 20 secondes.
- **R4** : Si le message contient `Nombre_total_aliens > Seuil_critique` ou `Type_alien_dangereux_détecté`, le Boss :
  - Envoie des soldats spécifiques.
  - Interagit avec l’arsenal pour demander et construire des armes adaptées.
| Type d'Alien  | Armes           | Temps de fabrication |
|---------------|----------------|---------------------|
| Hostile       | Armes lourdes   | 10 s                |
| Opportuniste  | Armes standard  | 5 s                 |
| Allié         | Armes non létales | 3 s               |
- **R5** : Le Boss attribue des rôles selon l'expérience des soldats.
  - Les soldats expérimentés reçoivent des armes lourdes et sont envoyés contre les Aliens Hostiles.
  - Les soldats débutants reçoivent des armes standards et sont affectés à des Aliens Opportunistes ou Alliés.
- **R6** : À la fin de la bataille, le champ de bataille envoie un rapport au Boss contenant le nombre de morts et de blessés.
- **R7** : Les soldats blessés sont envoyés chez les médecins pour être soignés en 20 secondes.
## Diagramme
R1.png
R2R3.png
R4R5.png
R6.png
R7R8.png
## MicroServices Interaction
Schema.png
