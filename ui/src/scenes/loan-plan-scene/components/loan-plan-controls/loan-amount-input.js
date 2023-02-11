import React from 'react'

import Form from 'react-bootstrap/Form'

import { currencies } from 'utils/currencies'

const containerStyle = {
  width: '270px',
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

const selectStyle = {
  width: '55px',
  display: 'inline-block',
}

const LoanPlanInput = props => {
  const { label, value, onChange, currency, setCurrency, style } = props

  const mapCurrencyOptions = () => (
    Object.keys(currencies).map(key => 
      <option key={key} value={currencies[key].code}>{currencies[key].symbol}</option>
    )
  )

  const onCurrencyChange = event => {
    setCurrency(event.target.value)
  }

  return (
    <div style={{...containerStyle, ...style}}>
      <Form.Label style={labelStyle}>{label}</Form.Label>
      <div>
        <Form.Control
          type='text'
          style={inputStyle}
          size='sm'
          value={value || ''}
          onChange={onChange}
        />
        <Form.Select
            style={selectStyle}
            size='sm'
            value={currency}
            onChange={onCurrencyChange}
        >
          {mapCurrencyOptions()}
        </Form.Select>
      </div>
    </div>
  )
}

export default LoanPlanInput
