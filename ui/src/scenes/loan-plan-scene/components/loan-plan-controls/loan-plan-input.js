import React from 'react'

import Form from 'react-bootstrap/Form'

const containerStyle = {
  width: '215px',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'space-between',
}

const labelStyle = {
  marginBottom: '0px',
}

const inputStyle = {
  width: '100px',
  display: 'inline-block',
  direction: 'rtl',
}

const LoanPlanInput = props => {
  const { label, value, onChange, style } = props

  return (
    <div style={{...containerStyle, ...style}}>
      <Form.Label style={labelStyle}>{label}</Form.Label>
      <Form.Control
        type='text'
        style={inputStyle}
        size='sm'
        value={value || ''}
        onChange={onChange}
      />
    </div>
  )
}

export default LoanPlanInput
