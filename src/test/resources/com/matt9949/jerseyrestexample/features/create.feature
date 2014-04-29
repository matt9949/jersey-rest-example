@create
Feature: Create a Cat Resource
  Scenario: Create a cat
    Given a create cat request "create-valid"
    And the create cat request is valid
    When the client requests to create a cat
    Then the response code should be '201'
