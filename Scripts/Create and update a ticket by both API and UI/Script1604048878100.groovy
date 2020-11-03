'create a new task by calling api'
String ticketKey = CustomKeywords.'sample.Common.createNewTicket'(project, issueType, summary)

'access to Jira and bypass authorization'
CustomKeywords.'sample.Common.accessJira'()

'update the name of the task on UI'
CustomKeywords.'sample.Common.updateExistingTicket'(ticketKey, newSummary)