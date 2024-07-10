import { Resource } from "./resource";

export interface Sale {
    id?: number;
    resource?: Resource;
    date?: Date;
    amount: number;
    cost: number;
    isSold: boolean;
}