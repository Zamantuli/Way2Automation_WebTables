@UserManagement
Feature: User Management in Way2Automation

  Scenario: Add and Validate Users in User List Table
    Given I am on the User List Table page
    Then I validate the table headers contain "First Name", "Last Name", and "Email"
    And I take a screenshot of the User List Table
    When I click on "Add User"
    Then I should see the Add User screen
    And I take a screenshot of the Add User screen
    When I add 3 random users
    Then I validate the users are added to the table
    And I take a screenshot of the User Table screen

