*** Settings ***
Library    ru.stqa.pft.addressbook.appmanager.rf.AddressbookKeywords
Suit Setup    Init Application Manager
Suit Teardown    Stop Application Manager

*** Test Cases ***
Can Create Group With Valid Data
    ${old_count} =    Get Group Count
    Create Group    test name    test header    test footer
    ${new_count} =    Get Group Count
    ${expected_count} =    Evaluate      ${old_count} + 1
    Should Be Equal As Integers     ${new_count}    ${expected_count}
