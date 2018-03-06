*** Settings ***
Library     ru.maistrenko.addressbook.robotFramework.AddressbookKeywords
Suite Setup     Init Application Manager
Suite Teardown   Stop Application Manager

*** Test Cases ***
Creation contact with valid data
    ${old_count} =  Get Contact Count
    Create Contact    first name  last name    address
    ${new_count} =  Get Contact Count
    ${expected_count} =    Evaluate     ${old_count} + 1
    Should Be Equal As Integers    ${new_count}     ${expected_count}


Remove contact
    ${old_count} =  Get Contact Count
    Remove Contact
    ${new_count} =  Get Contact Count
    ${expected_count} =    Evaluate     ${old_count} - 1
    Should Be Equal As Integers    ${new_count}     ${expected_count}