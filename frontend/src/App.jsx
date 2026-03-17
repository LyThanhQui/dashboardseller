import { useEffect, useState } from 'react'

function App() {
  const [data, setData] = useState(null)
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(true)

  const apiUrl = import.meta.env.VITE_ALERT_API_URL || '/alert'

  useEffect(() => {
    const fetchAlert = async () => {
      try {
        const response = await fetch(apiUrl)
        if (!response.ok) {
          throw new Error(`API error: ${response.status}`)
        }

        const alertData = await response.json()
        setData(alertData)
      } catch (err) {
        setError(err.message)
      } finally {
        setLoading(false)
      }
    }

    fetchAlert()
  }, [apiUrl])

  if (loading) {
    return <main className="card">Đang tải dữ liệu cảnh báo...</main>
  }

  if (error) {
    return <main className="card error">Không gọi được API: {error}</main>
  }

  const isDanger = data.alert

  return (
    <main className="card">
      <h1>Dashboard Seller</h1>
      <p>
        <strong>Cancel Rate:</strong> {data.cancelRate}
      </p>
      <p>
        <strong>Alert:</strong> {String(data.alert)}
      </p>
      <div className={isDanger ? 'status danger' : 'status ok'}>
        {isDanger ? 'NGUY HIỂM' : 'OK'}
      </div>
    </main>
  )
}

export default App
