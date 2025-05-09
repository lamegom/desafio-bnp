
  export interface MoveID {
    month: any;
    year: any;
    release: any;
    idProduct: string;
    idCosif: string;
  };
  export interface Move {
    id:MoveID; 
    description: string;
    dtMovement: number;
    user: string;
    amount: string;
    cosif: string;
    produto: string
  }
