Feature: List creation

  Scenario Outline: Create new List and verify its 'Active' status
    Given I log in to schoolData
    When I create new List with parameters:
      | Type   | Institution Types   | Geography   | Personnel   | Names   | ParentPID   | ParentInst   | UltParentPID   | UltParentInst   |
      | <Type> | <Institution Types> | <Geography> | <Personnel> | <Names> | <ParentPID> | <ParentInst> | <UltParentPID> | <UltParentInst> |
    And I activate the List
    Then List status is Active

    Examples:
      | Type        | Institution Types        | Geography | Personnel  | Names | ParentPID | ParentInst | UltParentPID | UltParentInst |
      | All         | Bureau of Indian Affairs |           |            |       |           |            |              |               |
      | Email       |                          | Alaska    | Journalism |       | +         |            |              |               |
      | Direct Mail |                          |           |            | 77777 | +         | +          |              |               |