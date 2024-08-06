import { Resource } from "./resource";
import { StockMapping } from "./stock-mapping";

export interface Sale {
    id?: number;
    resource?: Resource;
    date?: Date;
    amount: number;
    cost: number;
    isSold: boolean;
    stockMappingIds?: StockMapping[] | null;
}