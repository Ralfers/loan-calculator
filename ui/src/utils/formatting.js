import BigNumber from 'bignumber.js'

import { currencies } from './currencies'

export const formatAmount = (amount, currency) => {
  const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: currency
  })

  const decimals = currencies[currency].decimals
  const convertedAmount = new BigNumber(amount)
      .dividedBy(Math.pow(10, decimals))
      .toString()

  return formatter.format(convertedAmount)
}

export const formatInterest = interest => {
  const convertedInterest = new BigNumber(interest).multipliedBy(100).toString()

  return convertedInterest + ' %'
}
