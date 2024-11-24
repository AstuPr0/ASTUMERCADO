const mp = new MercadoPago('APP_USR-22dde298-6b58-4a1f-a497-6b0e186f5918', {
    locale: 'es-CO'
  });
  
  const createCheckoutButton = async (carritoId) => {
    try {
      const response = await fetch('/api/mercadopago', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ carritoId: carritoId })
      });
  
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.error || 'Error en la solicitud de pago');
      }
  
      const preference = await response.json();
      const brickBuilder = mp.bricks();
  
      if (window.checkoutButtonLg) window.checkoutButtonLg.unmount();
      window.checkoutButtonLg = await brickBuilder.create("wallet", "wallet_container_lg", {
        initialization: {
          preferenceId: preference.id
        }
      });
    } catch (error) {
      console.error('Error:', error);
      alert('Hubo un error al procesar el pago. Por favor, inténtalo de nuevo.');
    }
  };