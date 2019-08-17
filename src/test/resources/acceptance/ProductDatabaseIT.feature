Feature: Product Queries

  @DATABASE
  Scenario: I can query the products table for items by location

    Given The database is stocked with items
    When I query for product location A3
    Then I should return only matching items