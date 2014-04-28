@create
Feature: Create a Cat Resource
  Scenario: Create a cat
    Given a cat "create-valid"
    When the client requests to create a cat
    Then the response code should be '201'
    And the http header content location should be set