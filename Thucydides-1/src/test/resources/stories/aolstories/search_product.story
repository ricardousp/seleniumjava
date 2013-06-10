Scenario: Searching product on Homepage
@Ignore
Given I am a shopper
When I am viewing HomePage
Then I search for <product>
Then I enter <product> in search box
Then I click search button


Examples:
|product|
|Fridges and Freezers|
|Small Appliances|