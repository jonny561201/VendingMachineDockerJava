Feature: Product Queries

  @API
  Scenario: I can get a successful healthCheck
    When I query the healthCheck endpoint
    Then I get a success status code

  @API
  Scenario: I can purchase a product
    Given The database is stocked with Starburst at B10 for $0.75
    When I purchase a product at location B10
    Then I should successfully purchase product