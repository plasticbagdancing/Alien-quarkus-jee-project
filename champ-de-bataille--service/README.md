# Système de Défense de Station Spatiale

## Objectif du Système à Modéliser
Modéliser un système de gestion pour organiser la défense d'une station spatiale contre des attaques extraterrestres.

Le système permettra :
- De détecter les attaques extraterrestres et se défendre efficacement.
- D’assigner des rôles stratégiques aux habitants (défense et soins).
- De gérer les ressources (armes, soldats, soins) de manière optimale.

---

## Scénario : *"Colonie Perdue"*
Dans un futur proche, une colonie humaine s’est installée sur une planète lointaine, **Cybertron**, pour exploiter ses ressources. Les tensions montent lorsqu’une race extraterrestre, les **Mucucu**, revendique la planète comme leur territoire ancestral.  
Une nuit, la colonie subit une attaque brutale, forçant le commandant **Boss** à agir et les soldats à se défendre.

---

## Règles Métier

**R1** : Les Aliens sont générés aléatoirement par type (*hostile*, *neutre*, *amical*) et assignés à des salles spécifiques dans le champ de bataille.

**R2** : Les informations sur les Aliens sont collectées par le champ de bataille et transmises au radar.

**R3** : Le radar transmet ces informations à Détection, qui doit notifier le Boss dans un délai maximal de 20 secondes.

**R4** : Si le message contient `Nombre_total_aliens > Seuil_critique` ou `Type_alien_dangereux` détecté, le Boss :
- Envoie des soldats spécifiques.
- Interagit avec l’arsenal pour demander et construire des armes adaptées.

**R5** : Logique de construction des armes :
- En fonction du type et du nombre d’Aliens, le Boss détermine les besoins en armes.

| Type d'Alien   | Type d'Armes       | Temps de Fabrication |
|----------------|-------------------|---------------------|
| Hostiles       | Armes lourdes     | 10 secondes         |
| Neutres        | Armes standards   | 5 secondes          |
| Amicaux        | Armes non-létales | 3 secondes          |

**R6** : Interaction avec les soldats  
Le Boss attribue des rôles selon l'expérience des soldats :
- Les soldats expérimentés reçoivent des armes lourdes et sont envoyés contre les Aliens Hostiles.
- Les soldats débutants reçoivent des armes standards et sont affectés à des Aliens Neutres ou Amicaux.

**R7** : À la fin de la bataille, le champ de bataille envoie un rapport au Boss contenant :
- Le nombre de morts.
- Le nombre de blessés.

**R8** : Les soldats blessés sont envoyés chez les médecins pour être soignés en 20 secondes.
