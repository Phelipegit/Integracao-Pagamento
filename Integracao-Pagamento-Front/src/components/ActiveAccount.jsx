import { useEffect, useState } from 'react'
import { useParams, useSearchParams, Link } from 'react-router-dom'
import '../css/ActiveAccount.css'

export default function ActiveAccount() {
    const { id } = useParams()
    const [searchParams] = useSearchParams()
    const email = searchParams.get('email')

    const [status, setStatus] = useState('loading') // 'loading' | 'success' | 'error'
    const [errorMessage, setErrorMessage] = useState('')
    const URL_API = "https://api.phelipedev.com.br"

    useEffect(() => {

        async function activateAccount() {
            if (!id || !email) {
                setStatus('error')
                setErrorMessage('Link de verificação inválido.')
                return
            }

            try {
                const response = await fetch(`http://localhost:8080/auth/verify-account/${id}?email=${email}`, {
                    method:"GET"
                });

                const data = await response.json();


                if (response.ok && data.success) {
                    setStatus('success')
                } else {
                    setStatus('error')
                    setErrorMessage(data.message || 'Não foi possível verificar sua conta.')
                }
            } catch (err) {
                setStatus('error')
                setErrorMessage("Erro interno de servidor");
            }
        }

        activateAccount();

        return () => {
            cancelled = true
        }
    },[email,id])

    return (
        <div className="page">
            <div className="bg-grid" aria-hidden="true" />
            <div className="bg-glow" aria-hidden="true" />

            <main className="shell">
                <section className="card">
                    {status === 'loading' && (
                        <div className="verify">
                            <div className="verify__spinner" aria-hidden="true" />
                            <h2 className="verify__title">Verificando sua conta...</h2>
                            <p className="verify__text">Isso leva só um instante.</p>
                        </div>
                    )}

                    {status === 'success' && (
                        <div className="verify">
                            <div className="verify__icon verify__icon--ok">
                                <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
                                    <path d="M5 13l4 4L19 7" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
                                </svg>
                            </div>
                            <h2 className="verify__title">E-mail verificado com sucesso</h2>
                            <p className="verify__text">Sua conta já está ativa. Você já pode entrar.</p>
                            <Link to="/login" className="btn">
                                Ir para login
                                <svg viewBox="0 0 24 24" aria-hidden="true" className="btn__icon">
                                    <path d="M4 12h15M13 6l6 6-6 6" stroke="currentColor" strokeWidth="2" fill="none" />
                                </svg>
                            </Link>
                        </div>
                    )}

                    {status === 'error' && (
                        <div className="verify">
                            <div className="verify__icon verify__icon--err">
                                <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
                                    <path d="M6 6l12 12M18 6L6 18" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />
                                </svg>
                            </div>
                            <h2 className="verify__title">Não foi possível verificar</h2>
                            <p className="verify__text">{errorMessage}</p>
                            <Link to="/register" className="verify__link">Voltar para login</Link>
                        </div>
                    )}
                </section>
            </main>
        </div>
    )
}