import React from 'react';
import {Icon, Menu} from 'semantic-ui-react'

const pageUnitCount = 5;
const Pagination = (props) => {
    let minPage = 1;
    let maxPage = props.count;
    let currentPage = props.page;
    let sideUnitCount = (pageUnitCount - 1) / 2;
    let firstUnitPage = currentPage - sideUnitCount <= 0 ? minPage : currentPage - sideUnitCount;
    let lastUnitPage = currentPage + sideUnitCount > maxPage ? maxPage : currentPage + sideUnitCount;
    let needFirstPageMovingUnit = minPage === firstUnitPage ? false : true;
    let needLastPageMovingUnit = maxPage === lastUnitPage ? false : true;
    let pageUIs = Array(lastUnitPage-firstUnitPage+1).fill(firstUnitPage)
        .map((x, y) => x + y)
        .map((page) => {
            return (
                <Menu.Item
                    active={page === currentPage}
                    key={page} as='a'
                    onClick={() => props.onPageClick(props.searchApiIdx, page)}
                >
                    {page}
                </Menu.Item>
            );
        });

    return (
        <Menu floated='right' pagination>
            {needFirstPageMovingUnit
                ? <Menu.Item as='a' icon onClick={() => props.onPageClick(props.searchApiIdx, firstUnitPage)}>
                    <Icon name='chevron left'/>
                </Menu.Item>
            : ''}
            {pageUIs}
            {needLastPageMovingUnit
            ? <Menu.Item as='a' icon onClick={() => props.onPageClick(props.searchApiIdx, lastUnitPage)}>
                    <Icon name='chevron right'/>
                </Menu.Item>
            : ''}
        </Menu>
    );
};

export default Pagination;