*** Settings ***
Library     ru.maistrenko.addressbook.robotFramework.AddressbookKeywords
Suite Setup     Init Application Manager
Suite Teardown   Stop Application Manager

*** Test Cases ***
Creation group with valid data
    ${old_count} =  Get Group Count
    Create Group    test name  test header    test footer
    ${new_count} =  Get Group Count
    ${expected_count} =    Evaluate     ${old_count} + 1
    Should Be Equal As Integers    ${new_count}     ${expected_count}


Remove group
    ${old_count} =  Get Group Count
    Remove Group
    ${new_count} =  Get Group Count
    ${expected_count} =    Evaluate     ${old_count} - 1
    Should Be Equal As Integers    ${new_count}     ${expected_count}