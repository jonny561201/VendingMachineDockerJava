Feature: Product Queries

  Scenario: I can get a successful healthCheck
    When I query the healthCheck endpoint
    Then I get a success status code