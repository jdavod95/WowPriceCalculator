"wowStockCalc" is an application for supporting the usage of the World of Warcraft ingame economy. While it is created for a game economy, it might be applicable in other scenarios also.
The goal of the application is to help the user to make decisions, what items are worth producing and to keep track of item quantity and price.
The application requires a database and currently supports CRUD functionality for two types of data, Resources and Sales, which means it is possible to keep track of purchases.
Planned features:
	- Recipes:
		- recipes can be defined with existing resources
		- used to simulate the required materials for a specific item
		- calculates the total cost of the resulting item.
		- other ingame modifiers and data can be specified on a recipe, and later changed	
	- Summaries:
		- summary is for showing data about expenses and gains
		- any resource or any recipe or all-time general summaries can be queried
	- Calculations:
		- hypothetical production scenarios can be simulated
		- recipe/resource prices can be tweaked inside a calculation to see hypothetical costs
		- can be saved for future use
	- Crafter:
		- ingame character data can be specified and used for recipes