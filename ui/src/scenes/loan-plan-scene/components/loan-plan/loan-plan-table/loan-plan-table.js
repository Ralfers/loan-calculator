import React from 'react'

import Table from 'react-bootstrap/Table'

import LoanTableHeader from './loan-table-header'
import LoanTableBody from './loan-table-body'

const LoanPlanTable = props => {
  const { loanPlan } = props  

  return (
    <Table striped bordered hover>
        <LoanTableHeader />
        <LoanTableBody loanPlan={loanPlan} />
      </Table>
  )
}

export default LoanPlanTable
