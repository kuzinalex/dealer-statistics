# dealer-statistic

This is my diploma project on LeverX Group Java courses. 

The goal of the project is to make **RESTful API** that helps to give an independent rating to traders of in-game items (CS: GO, Fifa, Dota, Team Fortress, etc.). The rating is based on reviews that are offered by everyone, while reviews are thoroughly checked by admins. 

There are three roles: Administrator, Trader and Anonymous.

## Usage scenarios:

1. A **Trader** comes to the site, fills out a questionnaire to create his page on the site. After approving account by email trader can create adverts with items he wants to trade. Trader can also leave reviews to other traders.
2. An **Anonymous** comes to the site, finds the trader's page, leaves a review. The administrator checks the review, makes an approval or decline.
3. An **Administrator** comes to the site, finds new reviews and approvs or declines them.

## Main functionality:
1. Creation of traders pages.
2. Creating reviews for traders.
3. Calculation of the trader's rating.
4. Calculation of the overall top traders based on their ratings.
5. Filter by games and min-max ratings.


## Registration and authorization
Registration should take place according to the following scenario:
1. the user enter the required registration data.
2. the system generates a confirmation link (code), adds it to **Redis** and
sends it to the entered **email**, email codes have a lifetime of 24 hours.
3. following the link, **/ users / {user} / auth / confirm / {code}**, the system looks for the code in **Redis** and
if there is, it activates the user and makes it possible to log in
4. while the user has not confirmed the email, when trying to authorize the user will receive a corresponding error.

![image](https://user-images.githubusercontent.com/44968219/120216952-58e48980-c240-11eb-8587-00030f0e10fd.png)




## Entities diagram: 

![dealer-stat-db-image](https://user-images.githubusercontent.com/44968219/120212519-c097d600-c23a-11eb-9e97-210f50c6e5c3.png)

## How to use:
1. Clone and build project.
2. Run project.



