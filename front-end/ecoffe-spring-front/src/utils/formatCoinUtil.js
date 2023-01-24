function modedaBr (valor){
    return valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
};

export { modedaBr };