# rightmove-property-date-searcher
Project to find rental properties that are available between two specific dates. 

When looking for rental properties, I noticed that there was no way to filter by the avaliable date. So I created this little java project to help with this problem. Note this is based on the rightmove HTML as of 28/03/2021 so may not work in the future.

# How to run the project
1. cd into the root of the project
2. run "mvn clean install"
3. run "java -cp target/PropertyFinder-1.0-SNAPSHOT-jar-with-dependencies.jar Main"
4. You will be asked to enter two dates followed by a rightmove url
5. It will then start to open property links in your browser

Notes:
This is an example right move url: https://www.rightmove.co.uk/property-to-rent/find.html?searchType=RENT&locationIdentifier=REGION%5E87490&insId=1&radius=0.0&minPrice=&maxPrice=&minBedrooms=&maxBedrooms=&displayPropertyType=&maxDaysSinceAdded=&sortByPriceDescending=&_includeLetAgreed=on&primaryDisplayPropertyType=&secondaryDisplayPropertyType=&oldDisplayPropertyType=&oldPrimaryDisplayPropertyType=&letType=&letFurnishType=&houseFlatShare=

The first date should be the earliest date you are avalaible to move into a property.
The second date should be the latest date you are looking to move. 
